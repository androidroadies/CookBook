package com.c4n.cookbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static String DATABASENAME = "cookbook.db";
	public static String PRODUCTTABLE = "producttable";

	public static String colProductId = "productId";
	public static String colProductName = "productname";
	public static String colProductDesc = "productdesc";

	public static String colProductState = "productstate";
	public static String colProductDirection = "productdirection";
	public static String colProductURL = "producturl";

	private ArrayList<Item> cartList = new ArrayList<Item>();
	Context c;

	public DatabaseHelper(Context context) {
		super(context, DATABASENAME, null, 33);
		c = context;
	}

	/*
	 * "food_id": "9", "food_title": "Indian Soup", "food_state": "India",
	 * "food_cat": "8", "food_desc":
	 * "\"\r\n\r\nLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
	 * , "direction":
	 * "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop pu"
	 * , "food_image_url": "http://care4nature.org/food/images/big-details.png"
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + PRODUCTTABLE + "(" + colProductId
				+ " TEXT ," + colProductName + "	TEXT ," + colProductDesc
				+ "	TEXT ," + colProductState + " TEXT ," + colProductDirection
				+ " TEXT ," + colProductURL + " TEXT )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + PRODUCTTABLE);
		onCreate(db);
	}

	public void addProductToCart(Item productitem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put(colProductId, productitem.id);
		contentValues.put(colProductName, productitem.name);
		contentValues.put(colProductState, productitem.state);

		contentValues.put(colProductDesc, productitem.desc);
		contentValues.put(colProductDirection, productitem.direction);
		contentValues.put(colProductURL, productitem.url);

		db.insert(PRODUCTTABLE, colProductName, contentValues);
		// db.insert(PRODUCTTABLE, colProductDesc, contentValues);
		// db.insert(PRODUCTTABLE, colProductDirection, contentValues);
		// db.insert(PRODUCTTABLE, colProductURL, contentValues);

		/*
		 * colProductId, , , colProductDesc,,
		 */
		db.close();

	}

	// update

	public void removeProductFromCart(int productid) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("delete from producttable where productId=" + productid);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Item> getCartItems() {

		cartList.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + PRODUCTTABLE, null);
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					Item item = new Item();
					item.id = cursor.getString(cursor
							.getColumnIndex(colProductId));
					item.name = cursor.getString(cursor
							.getColumnIndex(colProductName));

					item.desc = cursor.getString(cursor
							.getColumnIndex(colProductDesc));

					item.state = cursor.getString(cursor
							.getColumnIndex(colProductState));

					item.direction = cursor.getString(cursor
							.getColumnIndex(colProductDirection));
					item.url = cursor.getString(cursor
							.getColumnIndex(colProductURL));
					cartList.add(item);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return cartList;
	}

}
