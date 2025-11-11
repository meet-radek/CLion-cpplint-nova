package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project

/**
 * Quick fix to add space after semicolon in for loops.
 *
 * This fix addresses `whitespace/semicolon` violations by ensuring semicolons
 * in for statements are followed by a space.
 *
 * Examples:
 * - for (int i=0;i<10;i++) → for (int i=0; i<10; i++)
 * - for (;i<10;i++) → for (; i<10; i++)
 *
 * Note: This fix only applies to for loops, not to semicolons at end of statements.
 */
class AddSpaceAfterSemicolonFix : LocalQuickFix {

    override fun getFamilyName(): String = "Add space after semicolon"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val document = QuickFixesManager.getDocument(project, descriptor) ?: return

        // Get the text range of the problem
        val textRange = descriptor.textRangeInElement ?: descriptor.psiElement.textRange ?: return

        // Get the line where the problem occurs
        val problemOffset = textRange.startOffset
        val lineNumber = document.getLineNumber(problemOffset)
        val lineStartOffset = document.getLineStartOffset(lineNumber)
        val lineEndOffset = document.getLineEndOffset(lineNumber)

        // Get the line text
        val lineText = document.getText(com.intellij.openapi.util.TextRange(lineStartOffset, lineEndOffset))

        // Only apply this fix if the line contains a for statement
        // Add space after semicolons that are not followed by whitespace or closing paren
        // Pattern: ;([^\s)]) → ; $1
        val fixedLine = if (lineText.contains("for")) {
            lineText.replace(Regex(";([^\\s)])"), "; $1")
        } else {
            lineText
        }

        // Only modify if there's actually a change
        if (fixedLine != lineText) {
            document.replaceString(lineStartOffset, lineEndOffset, fixedLine)
            FileDocumentManager.getInstance().saveDocument(document)
        }
    }
}
