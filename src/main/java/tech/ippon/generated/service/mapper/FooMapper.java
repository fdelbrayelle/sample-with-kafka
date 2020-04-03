package tech.ippon.generated.service.mapper;


import tech.ippon.generated.domain.*;
import tech.ippon.generated.service.dto.FooDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Foo} and its DTO {@link FooDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FooMapper extends EntityMapper<FooDTO, Foo> {



    default Foo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Foo foo = new Foo();
        foo.setId(id);
        return foo;
    }
}
