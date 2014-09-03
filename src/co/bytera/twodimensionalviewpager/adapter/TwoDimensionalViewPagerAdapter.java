/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.bytera.twodimensionalviewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import co.bytera.twodimensionalviewpager.R;

public class TwoDimensionalViewPagerAdapter extends PagerAdapter {

	/*
	 *  For the demonstration purpose, 2-D of resource(integer) array is used.
	 *  However, any types of data are able to be set.
	 *  Array can be ragged as well.
	 */
	private int[][] data = new int[0][0];
	private Context context;
	private int rowNumber = 0;
	
	/*
	 *  Constructor: inject context here
	 */
	public TwoDimensionalViewPagerAdapter(Context context) {
		this.context = context;
	}

	/*
	 *  Set 2-D data
	 */
	public void setData(int[][] data) {
		this.data = data;
	}
	
	/*
	 *  Move page to the next row
	 */
	public void nextRow() {
		if(rowNumber < data.length -1)
			rowNumber++;
	}
	
	/*
	 *  Move page to the previous row
	 */
	public void previousRow() {
		if(rowNumber > 0)
			rowNumber--;
	}
	
	/*
	 * getCount() returns the number of items in the current row.
	 */
	@Override
	public int getCount() {
		return data[rowNumber].length;
	}
	
	/*
	 * Important: getItemPosition returns POSITION_NONE
	 *    Otherwise, it PagerAdapter won't respond to the notifyDataSetChanged()
	 */
	@Override 
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		@SuppressWarnings("static-access")
		LayoutInflater inflater = (LayoutInflater)context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		/*
		 * Layout is created from xml here, but it can be done by java coding as well
		 */
		View view = inflater.inflate(R.layout.view_page, null);
		
		TextView textView = (TextView)view.findViewById(R.id.textView1);
		textView.setText(rowNumber + ", " + position);
		
		ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
		imageView.setImageResource(data[rowNumber][position]);
		
		container.addView(view);
		
		return view;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View)arg1);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}
}
