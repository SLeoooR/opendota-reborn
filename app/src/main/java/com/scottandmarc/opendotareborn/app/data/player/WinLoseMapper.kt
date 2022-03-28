package com.scottandmarc.opendotareborn.app.data.player

import com.scottandmarc.opendotareborn.app.domain.entities.WinLose

object WinLoseMapper {
    fun RemoteWinLose.toDomain(): WinLose = WinLose(
        this.id,
        this.win,
        this.lose
    )

    fun WinLose.toLocal() = LocalWinLose(
        this.id,
        this.win,
        this.lose
    )

    fun LocalWinLose.toDomain() = WinLose(
        this.id,
        this.win,
        this.lose
    )
}