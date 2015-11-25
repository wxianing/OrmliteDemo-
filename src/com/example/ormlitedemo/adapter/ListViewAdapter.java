package com.example.ormlitedemo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ormlitedemo.R;
import com.example.ormlitedemo.bean.Person;

public class ListViewAdapter extends BaseAdapter {

	private List<Person> data;
	private Context context;

	public ListViewAdapter(List<Person> data, Context context) {
		super();
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item,
					null);
		}
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView address = (TextView) convertView.findViewById(R.id.address);
		name.setText(data.get(position).getName());
		address.setText(data.get(position).getAddress());

		return convertView;
	}

}
