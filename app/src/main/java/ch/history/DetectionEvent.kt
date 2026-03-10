package ch.pocketpc.nearbyglasses.history

import androidx.room.Entity
import androidx.room.PrimaryKey

//this file represents a single detection event that is stored locally

/**DATA STORED
 * -Bluetooth manufactorer company ID
 * translated label rerived from said ID
 * RSSI at moment of detection
 * millisecond timestamp
 * 
 * like readme mentoed, no MAC address or location data or personal info stored
 */
@Entity(tableName ="detection_events")
data class DetectEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    //time in millisecond at detection alert trigger
    val timestampMs: Long,
    //comapny id brought from event
    val companyId: String
    //translation to user-readable text
    val manufacturerName: String
    //RSSI value
    val rssi: Int
)