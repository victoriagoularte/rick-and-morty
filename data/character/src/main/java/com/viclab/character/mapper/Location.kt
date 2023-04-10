package com.viclab.character.mapper

import com.viclab.character.model.CharacterResponse
import com.viclab.character.model.LocationEntity
import com.viclab.model.character.Character

fun CharacterResponse.LocationResponse.asEntity() = LocationEntity(
    name = name,
    url = url,
)

fun LocationEntity.asLocation() = Character.Location(
    name = name,
    url = url,
)