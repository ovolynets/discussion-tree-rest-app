package de.ovolynets.discussiontree.persistence.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import de.ovolynets.discussiontree.domain.MessageEntry;
import de.ovolynets.discussiontree.persistence.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private List<MessageEntry> roots = Lists.newArrayList();

    // Keep it as the counter for all posts
    private AtomicInteger counter = new AtomicInteger(1);

    @Override
    public int addNew(final String text) {
        int newId = counter.incrementAndGet();
        roots.add(new MessageEntry(text, newId, Lists.newArrayList()));
        return newId;
    }

    @Override
    public Optional<Integer> addReply(final int postId, final String text) {
        return get(postId).map(entry -> {
                int newCounter = counter.incrementAndGet();
                MessageEntry newEntry = new MessageEntry(text, newCounter, Lists.newArrayList());
                entry.getChildren().add(newEntry);
                return newCounter;
            });
    }

    @Override
    public Optional<MessageEntry> get(final int postId) {
        return roots.stream().filter(entry -> entry.find(postId) != null).findAny();
    }

    @Override
    public Optional<MessageEntry> delete(final int postId) {
        return roots.stream().filter(entry -> entry.delete(postId) != null).findAny();
    }
}
