package by.epam.informationhandling.component.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite implements Component {

    private List<Component> children = new ArrayList<>();

    public Composite() {

    }

    public Composite(List<? extends Component> children) {
        this.children.addAll(children);
    }

    public List<Component> getChildren() {
        return children;
    }

    public void add(Component child) {
        children.add(child);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }

        if (object == null || getClass() != object.getClass()){
            return false;
        }

        Composite composite = (Composite) object;
        return Objects.equals(children, composite.children);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Composite= {" +
                "children=" + children +
                '}';
    }
}
