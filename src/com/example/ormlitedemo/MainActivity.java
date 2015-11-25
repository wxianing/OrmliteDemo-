package com.example.ormlitedemo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ormlitedemo.adapter.ListViewAdapter;
import com.example.ormlitedemo.bean.Person;
import com.example.ormlitedemo.bean.User;
import com.example.ormlitedemo.dao.DatabaseHelper;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button addBtn;
	private Button addBtn1;
	private Button query;
	private ListView lv;
	private ListViewAdapter adapter;
	private List<Person> personList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		addBtn = (Button) this.findViewById(R.id.add_btn);
		addBtn.setOnClickListener(this);

		query = (Button) findViewById(R.id.query_btn);
		query.setOnClickListener(this);

		lv = (ListView) findViewById(R.id.lv);
		personList = new ArrayList<Person>();
		adapter = new ListViewAdapter(personList, this);
		lv.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		DatabaseHelper helper;
		switch (v.getId()) {
		case R.id.add_btn:

			User u1;
			Person person = new Person("黎明", "北京");
			helper = DatabaseHelper.getHelper(MainActivity.this);
			try {
				helper.getPersonDao().create(person);
				for (int i = 0; i < 20; i++) {
					u1 = new User("name" + i, "2B青年" + i);
					helper.getUserDao().create(u1);
					person = new Person("Lucy", "深圳" + i);
					helper.getPersonDao().create(person);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		case R.id.query_btn:
			helper = DatabaseHelper.getHelper(this);
			try {
				List<Person> persons = helper.getPersonDao().queryForAll();
				Toast.makeText(this, ">>>" + persons.size(), 0).show();
				personList.addAll(persons);
				adapter.notifyDataSetChanged();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
