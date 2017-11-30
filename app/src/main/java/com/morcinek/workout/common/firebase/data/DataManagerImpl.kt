package com.morcinek.workout.common.firebase.data

import com.google.firebase.database.DatabaseReference

open class DataManagerImpl(override val reference: DatabaseReference) : DataManager()