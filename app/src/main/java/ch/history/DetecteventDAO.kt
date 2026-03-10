package ch.pocketpc.nearbyglasses.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@DAO
//insert a new detection. Ignore conflicts (shouldnt happen with autoGenerate PK)
interface DetectionEventDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: DetectionEvent)
}
//observe all events, sorted from newest first and impliment History UI
@Query("SELECT * FROM detection_events ORDER BY timestampMs DESC")
fun observeAll(): Flow<List<DetectionEvent>>
//One-shot fetch
@Query("SELECT * FROM detection_events ORDER BY timestampMs DESC")
suspend fun getAll(): List<DetectionEvent>
//delete all stored events
@Query("DELETE FROM detection_events")
suspend fun deleteAll()
//delete events that are older than a specified miilisecond stamp
@Query("DELET FROM detection_Events WHERE timestampMs < :cutoffMs")
suspend fun deleteOlderThan(cutoffMs: Long)

//total number of stored events
@Query("Select Count(*)FROM detection_events")
suspend fun count(): Int