package bg.softuni.fooddelivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private final Long objectId;

    private final String objectType;

    public Long getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public ObjectNotFoundException(Long productId,
                                   String objectType) {
        this.objectId = productId;
        this.objectType = objectType;
    }
}
