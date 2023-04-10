package com.viclab.character.mapper

import com.viclab.character.model.CharacterResponse
import com.viclab.character.model.LocationEntity
import com.viclab.character.model.OriginEntity
import com.viclab.model.character.Character

fun CharacterResponse.OriginResponse.asEntity() = OriginEntity(
    name = name,
    url = url,
)

fun OriginEntity.asOrigin() = Character.Origin(
    name = name,
    url = url,
)
