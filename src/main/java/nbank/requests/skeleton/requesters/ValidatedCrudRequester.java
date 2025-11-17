package nbank.requests.skeleton.requesters;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import nbank.models.BaseModel;
import nbank.requests.skeleton.Endpoint;
import nbank.requests.skeleton.HttpRequest;
import nbank.requests.skeleton.interfaces.CrudEndpointInterface;

public class ValidatedCrudRequester<T extends BaseModel> extends HttpRequest implements CrudEndpointInterface {
    private CrudRequester crudRequester;

    public ValidatedCrudRequester(RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
        this.crudRequester = new CrudRequester(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    public T post(BaseModel model) {
        return (T) crudRequester.post(model).extract().as(endpoint.getResponseModel());
    }

    @Override
    public T get(long id) {
        return (T) crudRequester.get(id).extract().as(endpoint.getResponseModel());
    }

    @Override
    public T update(BaseModel model) {
        return (T) crudRequester.update(model).extract().as(endpoint.getResponseModel());
    }

    @Override
    public Object delete(long id) {
        return null;
    }
}