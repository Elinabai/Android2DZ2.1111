package com.geektech.android2dz21.interfaces

import com.geektech.android2dz21.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
}