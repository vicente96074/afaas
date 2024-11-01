package com.kojstarinnovations.afaas.commons.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonUserIdAndURLImg {

    public static JsonUserIdAndURLImg of(String identification, String urlProfilePicture) {
        return new JsonUserIdAndURLImg(identification, urlProfilePicture);
    }

    private String identification;
    private String urlProfilePicture;
}