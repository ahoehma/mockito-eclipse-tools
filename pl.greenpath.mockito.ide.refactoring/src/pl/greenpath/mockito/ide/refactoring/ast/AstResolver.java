package pl.greenpath.mockito.ide.refactoring.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * A helper class to find declarations based on given AST nodes
 * 
 * @author Michal Borek
 */
public class AstResolver {

    public MethodDeclaration findParentMethodBodyDeclaration(final ASTNode node) {
        if (node == null) {
            return null;
        }
        if (node instanceof MethodDeclaration) {
            return (MethodDeclaration) node;
        }
        return findParentMethodBodyDeclaration(node.getParent());
    }

    public BodyDeclaration findParentBodyDeclaration(final ASTNode node) {
        if (node == null) {
            return null;
        }
        if (node instanceof BodyDeclaration) {
            return (BodyDeclaration) node;
        }
        return findParentBodyDeclaration(node.getParent());
    }

    public ChildListPropertyDescriptor getBodyDeclarationsProperty(final ASTNode node) {
        if (node instanceof AbstractTypeDeclaration) {
            return ((AbstractTypeDeclaration) node).getBodyDeclarationsProperty();
        }
        if (node instanceof AnonymousClassDeclaration) {
            return AnonymousClassDeclaration.BODY_DECLARATIONS_PROPERTY;
        }
        throw new IllegalArgumentException("Only AbstractTypeDeclaration and AnonymousClassDeclaration allowed");
    }
}
