package com.viclab.character.mapper

import com.viclab.character.model.CharacterEntity
import com.viclab.character.model.CharacterResponse
import com.viclab.character.model.LocationEntity
import com.viclab.character.model.OriginEntity
import com.viclab.model.character.Character

fun CharacterEntity.asCharacter(originEntity: OriginEntity, locationEntity: LocationEntity) = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = originEntity.asOrigin(),
    location = locationEntity.asLocation(),
    image = image,
    episode = episode,
    url = url,
    created = created
)

fun CharacterResponse.asEntity(originId: Long, locationId: Long) = CharacterEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    originId = originId,
    locationId = locationId,
    image = image,
    episode = episode,
    url = url,
    created = created
)