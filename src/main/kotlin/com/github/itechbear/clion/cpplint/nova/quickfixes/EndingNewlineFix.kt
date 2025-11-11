package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project

/**
 * Quick fix to add a newline at the end of file.
 */
class EndingNewlineFix : LocalQuickFix {

    override fun getFamilyName(): String = "Add newline at end of file"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val document = QuickFixesManager.getDocument(project, descriptor) ?: return

        document.insertString(document.textLength, "\n")
        FileDocumentManager.getInstance().saveDocument(document)
    }
}
