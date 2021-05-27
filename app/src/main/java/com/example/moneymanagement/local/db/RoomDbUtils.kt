package com.example.moneymanagement.local.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object RoomDbUtils {

    const val DB_NAME = "app.db"
    const val DB_VERSION = 1

    fun getRoomDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
               // .addMigrations(Migration_1_2)
                .build()
    }

    private val Migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectLeft1' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectTop1' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectRight1' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectTop2' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectRight2' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectBottom1' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectLeft2' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'cropWindowRectBottom2' REAL")
            database.execSQL("ALTER TABLE 'S3ItemDownloadModel' ADD COLUMN 'rotatedDegrees' INTEGER")

            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectLeft1' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectTop1' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectRight1' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectTop2' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectRight2' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectBottom1' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectLeft2' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'cropWindowRectBottom2' REAL")
            database.execSQL("ALTER TABLE 'ImageItem' ADD COLUMN 'rotatedDegrees' INTEGER")
        }
    }


}