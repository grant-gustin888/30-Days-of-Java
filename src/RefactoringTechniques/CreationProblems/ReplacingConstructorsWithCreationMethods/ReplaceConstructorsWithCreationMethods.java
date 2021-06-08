package RefactoringTechniques.CreationProblems.ReplacingConstructorsWithCreationMethods;

public class ReplaceConstructorsWithCreationMethods {

    public static void main(String[] args) {
        // 1. Replace Constructors with Creation Methods.

        // Problems:
        // - hard to choose which constructor to use based on parameters because the constructor
        // names are vague -- especially overloading gone too far
        // - possible method signature conflicts, since constructors can't have the same attribute
        // signatures
        // - I want to have constructors with the same number of attribute signatures,
        // just different parameter names.

        // Solutions:
        // - give creation methods unique, more descriptive names
        // - this avoids method signature conflicts
    }
}
