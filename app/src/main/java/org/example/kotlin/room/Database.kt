package org.example.kotlin.room

import android.arch.persistence.room.*
import android.content.Context


@Entity
class User(
        @PrimaryKey val uid: Int = 0,
        @ColumnInfo(name = "first_name") val firstName: String,
        @ColumnInfo(name = "last_name") val lastName: String
)

@Dao
interface UserDao {
    @get:Query("SELECT * FROM user")
    val all: List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User?

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val instance: AppDatabase by lazy {
            Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
        }

        private lateinit var context: Context

        fun initialize(context: Context) {
            this.context = context.applicationContext
        }
    }
}