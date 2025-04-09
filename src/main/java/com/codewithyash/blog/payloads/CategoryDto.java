package com.codewithyash.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int categoryId;
    @NotBlank
    @Size(min = 4, message = "Minimum size of cat name is 4")
    private String categoryName;
    @NotBlank
    @Size(min = 5, message = "Minimum size of cat description is 5")
    private String categoryDescription;

}
