package com.thedancercodes.wikimobile.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class ArticleDatabaseOpenHelper(context: Context)
    : ManagedSQLiteOpenHelper(context, "ArticlesDatabase.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        // Define the tables in this database
        db?.createTable("Favorites", true, /* Favorites Table */
                "id" to INTEGER + PRIMARY_KEY,
                "title" to TEXT,
                "url" to TEXT,
                "thumbnailJson" to TEXT)

        db?.createTable("History", true, /* History Table */
                "id" to INTEGER + PRIMARY_KEY,
                "title" to TEXT,
                "url" to TEXT,
                "thumbnailJson" to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // Use to update the schema of the table if needed.
    }
}