package io.google.holidify;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
@Database(entities = { Destination.class ,
    }, version = 1,exportSchema = false)
public abstract class HolidifyDatabase extends RoomDatabase {

    private static final String DB_NAME = "Holidify.db";
    private static volatile HolidifyDatabase instance;

    public static synchronized HolidifyDatabase getInstance(Context context) {
      if (instance == null) {
        instance = create(context);
      }
      return instance;
    }

    private static HolidifyDatabase create(final Context context) {
      return Room.databaseBuilder(
          context,
          HolidifyDatabase.class,
          DB_NAME).allowMainThreadQueries().build();
    }

    public abstract DestinationDao getDestinationDao();

  }


