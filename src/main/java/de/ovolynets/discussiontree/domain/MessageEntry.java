package de.ovolynets.discussiontree.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
            Stream<MessageEntry> optionalStream = children.stream().filter(entry -> entry.find(postId) != null);
            return optionalStream.findFirst();
        }
    }
}
