package nbank.requests.skeleton.interfaces;

import nbank.models.BaseModel;

public interface CrudEndpointInterface {
    Object post(BaseModel model);

    Object get(long id);

    Object update(BaseModel model);

    Object delete(long id);
}
