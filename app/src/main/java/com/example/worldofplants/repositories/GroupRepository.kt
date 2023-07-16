package com.example.worldofplants.repositories

import com.example.worldofplants.data.dao.GroupDao
import com.example.worldofplants.data.model.GroupModel

class GroupRepository(private val groupDao: GroupDao) {

    suspend fun addGroups(groups: GroupModel) = groupDao.insert(groups)
}