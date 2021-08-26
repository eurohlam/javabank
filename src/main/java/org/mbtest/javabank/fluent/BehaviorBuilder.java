package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Behavior;
import org.mbtest.javabank.model.Copy;
import org.mbtest.javabank.model.Wait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BehaviorBuilder implements FluentBuilder {

    private final ResponseBuilder parent;

    private List<BehaviorTypeBuilder> childBehaviors;

    protected BehaviorBuilder(ResponseBuilder responseBuilder) {
        this.parent = responseBuilder;
        this.childBehaviors = new ArrayList<>();
    }

    @Override
    public ResponseBuilder end() {
        return parent;
    }

    public WaitBuilder wait_() {
        WaitBuilder wait = new WaitBuilder(this);
        childBehaviors.add(wait);
        return wait;
    }

    public CopyBuilder copy() {
        CopyBuilder copy = new CopyBuilder(this);
        childBehaviors.add(copy);
        return copy;
    }

    public List<BehaviorTypeBuilder> getBehaviors() {
        return childBehaviors;
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> build() {
        Map<String, Object> behaviors = new HashMap<>();
        childBehaviors.forEach(behaviorTypeBuilder -> {
            Behavior behavior = behaviorTypeBuilder.build();
            switch (behavior.getType()) {
                case COPY: {
                    List<Copy> copyList = (List<Copy>) behaviors.getOrDefault(behavior.getType().value(), new ArrayList<>());
                    copyList.add((Copy) behavior);
                    behaviors.put(behavior.getType().value(), copyList);
                    break;
                }
                case WAIT: {
                    behaviors.put(behavior.getType().value(),
                            ((Wait) behavior)
                                    .getFunction()
                                    .orElse(String.valueOf(((Wait) behavior).getValue())));
                    break;
                }
                default: break;
            }
        });
        return behaviors;
    }

}
