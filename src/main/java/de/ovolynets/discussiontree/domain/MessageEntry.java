package de.ovolynets.discussiontree.domain;

import java.util.List;
import java.util.Optional;

public class MessageEntry {

    private final String text;
    private final int id;
    private final List<MessageEntry> children;

    public MessageEntry(final String text, final int id, final List<MessageEntry> children) {
        this.text = text;
        this.id = id;
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public List<MessageEntry> getChildren() {
        return children;
    }

    public Optional<MessageEntry> find(final int postId) {
        if (postId == id) {
            return Optional.of(this);
        } else {
            return children.stream().filter(entry -> entry.find(postId) != null).findFirst();
        }
    }

    public Optional<MessageEntry> delete(final int postId) {
        // This part may not be fully complete. It should search for the entry in the children and delete it from there

        Optional<MessageEntry> maybeDeletedEntry = children.stream().filter(entry1 ->
                                                                   entry1.id == postId).findAny().map(entry -> {
                                                               children.remove(entry);
                                                               return entry;
                                                           });

        if (maybeDeletedEntry.isPresent()) {
            return maybeDeletedEntry;
        } else {
            return children.stream().filter(entry -> entry.delete(postId) != null).findAny();
        }
    }
}
