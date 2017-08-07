package de.ovolynets.discussiontree.domain;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(final int postId) {
        super("Post with id " + postId + "not found");
    }
}
