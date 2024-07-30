public class ToDoItem {
    private String description;
    private boolean isDone;

    // Constructor to initialize the item with the description and isDone as false
    public ToDoItem(String description) {
        this.description = description;
        this.isDone = false; // Initially, the item is not done
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for isDone
    public boolean isDone() {
        return isDone;
    }

    // Setter for isDone
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
