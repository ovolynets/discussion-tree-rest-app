package de.ovolynets.discussiontree.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ovolynets.discussiontree.domain.MessageEntry;
import de.ovolynets.discussiontree.persistence.DatabaseService;
import de.ovolynets.discussiontree.persistence.impl.DatabaseServiceImpl;

@RestController
@RequestMapping("/api/")
public class CRUDController {

    private final DatabaseService databaseService;

    @Autowired
    public CRUDController(final DatabaseServiceImpl databaseService) {
        this.databaseService = databaseService;
    }

    @PutMapping(value = "/posts/{text}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Integer> addNewPosts(@PathVariable("text") final String text) {
        int id = databaseService.addNew(text);
        return ResponseEntity.ok(id);
    }

    @PutMapping(value = "/posts/{postId}/reply/{text}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Integer> addReplyPosts(@PathVariable("postId") final int postId,
            @PathVariable("text") final String text) {
        Optional<Integer> newId = databaseService.addReply(postId, text);
        return newId.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<MessageEntry> getPosts(@PathVariable("postId") final int postId) {
        Optional<MessageEntry> result = databaseService.get(postId);
        return result.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Object> deletePosts(@PathVariable("postId") final int postId) {
        Optional<MessageEntry> result = databaseService.delete(postId);
        return result.map(entry -> ResponseEntity.ok().build()).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
