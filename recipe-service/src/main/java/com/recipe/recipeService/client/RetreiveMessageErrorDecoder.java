package com.recipe.recipeService.client;

import com.recipe.recipeService.errors.BadRequestAlertException;
import com.recipe.recipeService.errors.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response){
        ExceptionMessage message = null;
        try(InputStream body = response.body().asInputStream()) {
            message = new ExceptionMessage();
            message.setTimestamp(response.headers().get("date").toString());
            message.setStatus(response.status());
            message.setError(HttpStatus.resolve(response.status()).getReasonPhrase());
            message.setMessage(IOUtils.toString(body, StandardCharsets.UTF_8));
            message.setPath(response.request().url());

        }catch (IOException e){
            return new Exception(e.getMessage());
        }
        switch (response.status()){
            case 404:
                throw new BadRequestAlertException(message);
            default:
                return errorDecoder.decode(methodKey,response);
        }
    }
}
