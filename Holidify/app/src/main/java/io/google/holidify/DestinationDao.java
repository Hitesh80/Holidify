package io.google.holidify;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;
import java.util.List;
@Dao
interface DestinationDao {

  @Query("SELECT * FROM destination" )
  List<Destination> getAllDestinations();

  @Insert
  void insert(@NonNull Destination destination);

  @Update
  void update(@NonNull Destination destination);

  @Delete
  void delete(@NonNull Destination destination);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(@NonNull List<Destination> destinationList);
}
