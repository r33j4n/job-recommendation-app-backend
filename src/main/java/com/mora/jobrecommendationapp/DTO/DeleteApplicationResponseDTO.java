package com.mora.jobrecommendationapp.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteApplicationResponseDTO {

    private String message;
    private boolean isDeleted;
}
