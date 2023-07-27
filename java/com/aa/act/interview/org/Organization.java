import java.util.*;
public abstract class Organization {
    private Position root;
    private IdentifierGenerator employeeIdGenerator= new IdentifierGenerator();

    public Organization() {
        root = createOrganization();
    }

    protected abstract Position createOrganization();

    /**
     * hire the given person as an employee in the position that has that title
     * 
     * @param person
     * @param title
     * @return the newly filled position or empty if no position has that title
     */
    public Optional<Position> hire(Name person, String title) 
    {
        return hire(person, title, root);
    }

    public Optional<Position> hire(Name person, String title, Position position) 
    {
        if(position.getTitle()==title)
        {
            Employee newHire= new Employee(employeeIdGenerator.createNewIdentifier(), person);
            position.setEmployee(Optional.ofNullable(newHire));
            return Optional.ofNullable(position);
        }
        Iterator <Position> posIterator = position.getDirectReports().iterator();
        while (posIterator.hasNext())
        {
            Position p = posIterator.next();
            hire(person, title, p);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return printOrganization(root, "");
    }

    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}