package com.qwertify.project.mappers;

public interface EntityMapper<Entity, DomainModel> {

    DomainModel mapFromEntity(Entity entity);

    Entity mapToEntity(DomainModel domainModel);

}
