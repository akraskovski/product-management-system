package by.kraskovski.pms.application.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.Authority}.
 */
@Getter
@Setter
public class AuthorityDto {

    @NotNull
    private String id;

    @NotNull
    private String name;
}
