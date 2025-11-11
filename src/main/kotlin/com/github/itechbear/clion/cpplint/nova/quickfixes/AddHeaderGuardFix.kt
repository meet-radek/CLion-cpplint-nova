package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import java.io.File

/**
 * Quick fix to add header guard to C++ header files.
 */
class AddHeaderGuardFix : LocalQuickFix {

    override fun getFamilyName(): String = "Add header guard"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val document = QuickFixesManager.getDocument(project, descriptor) ?: return

        val filepath = descriptor.psiElement.containingFile.virtualFile.canonicalPath ?: return
        val root = project.basePath ?: return
        val relativePath = FileUtil.getRelativePath(File(root), File(filepath)) ?: return

        // Generate guard name from relative path: path/to/file.h -> PATH_TO_FILE_H_
        val guard = relativePath.replace(Regex("[^\\w]"), "_").uppercase() + "_"

        insertGuard(document, guard)
        FileDocumentManager.getInstance().saveDocument(document)
    }

    private fun insertGuard(document: Document, guard: String) {
        val headGuard = "#ifndef $guard\n#define $guard 1\n"
        document.insertString(0, headGuard)

        val tailGuard = "\n#endif  // $guard\n"
        document.insertString(document.textLength, tailGuard)
    }
}
