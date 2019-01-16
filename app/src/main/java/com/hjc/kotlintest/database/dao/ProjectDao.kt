package com.hjc.kotlintest.database.dao

import android.arch.persistence.room.*
import com.hjc.kotlintest.bean.ProjectBean

@Dao
interface ProjectDao {

    @get:Query("SELECT * FROM project")
    val all: List<ProjectBean.Data>

    @get:Query("SELECT COUNT(*) FROM project")
    val count: Int

    @Insert
    fun insert(vararg entities: ProjectBean.Data)

    @Delete
    fun delete(entity: ProjectBean.Data)

    @Query("DELETE FROM project")
    fun deleteAll()

    @Update
    fun update(entity: ProjectBean.Data)
}