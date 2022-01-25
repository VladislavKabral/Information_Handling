package by.epam.informationhandling.logic.comparator;

import by.epam.informationhandling.component.entity.Composite;

import java.util.Comparator;

public class SentenceCountComparator implements Comparator<Composite> {

    @Override
    public int compare(Composite firstComposite, Composite secondComposite) {
        return firstComposite.getChildrenCount() - secondComposite.getChildrenCount();
    }
}
