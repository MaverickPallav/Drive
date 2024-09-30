package patterns;

public abstract class FileOperation {
    public final void execute() {
        if (validate()) {
            synchronized (this) {
                performOperation();
                logOperation();
            }
        } else {
            System.out.println("Validation failed. Operation aborted.");
        }
    }

    protected abstract boolean validate();
    protected abstract void performOperation();
    protected void logOperation() {
        System.out.println("Operation performed: " + this.getClass().getSimpleName());
    }
}
