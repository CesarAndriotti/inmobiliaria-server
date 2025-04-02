package com.inmobiliaria.server;

public class MessageConstants {

    //SUCCESS MESSAGES
    public static final String SUCCESS_CREATE = "The resourse has been created successfully.";
    public static final String SUCCESS_UPDATE = "The resourse has been updated successfully";
    public static final String SUCCESS_DELETE = "The resourse has been deleted successfully.";
    public static final String LIST_EMPTY = "There aren't agents. The list is empty";
    public static final String IDENTICAL_DATA = "The provided data is identical to the existing data.";
    public static final String EXISTING_DATA = "A record already exists in the database.";
    
    //GENERIC ERRORS
    public static final String ERROR_NOT_FOUND = "The resource was not found.";
    public static final String ERROR_BAD_REQUEST = "The request is invalid.";
    public static final String ERROR_INTERNAL_SERVER = "An error occurred on the server.";

    //CRUD ERRORS
    public static final String ERROR_CREATE_FAILED = "The resource could not be created due to a server error.";
    public static final String ERROR_CREATE_UNSUCCESSFUL = "Failed to create the resource. Please try again later.";
    public static final String ERROR_DELETE_FAILED = "The resource could not be deleted. It may not exist.";
    public static final String ERROR_DELETE_UNSUCCESSFUL = "Failed to delete the resource. The resource may not be found.";
    public static final String ERROR_UPDATE_FAILED = "The resource could not be updated due to a server error.";
    public static final String ERROR_UPDATE_UNSUCCESSFUL = "Failed to update the resource. Please try again later.";
}
