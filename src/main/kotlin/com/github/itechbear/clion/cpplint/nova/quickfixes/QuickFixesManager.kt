package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager

/**
 * Registry of cpplint rule names mapped to quick fix implementations.
 *
 * Most rules don't have quick fixes yet (null values).
 * Only a few common fixes are implemented.
 */
object QuickFixesManager {

    private val fixes: Map<String, LocalQuickFix?> = mapOf(
        "build/class" to null,
        "build/c++11" to null,
        "build/deprecated" to null,
        "build/endif_comment" to null,
        "build/explicit_make_pair" to null,
        "build/forward_decl" to null,
        "build/header_guard" to AddHeaderGuardFix(),
        "build/include" to null,
        "build/include_alpha" to null,
        "build/include_order" to null,
        "build/include_what_you_use" to null,
        "build/namespaces" to null,
        "build/printf_format" to null,
        "build/storage_class" to null,
        "legal/copyright" to null, // AddCopyrightFix() - commented out in original
        "readability/alt_tokens" to null,
        "readability/braces" to null,
        "readability/casting" to null,
        "readability/check" to null,
        "readability/constructors" to null,
        "readability/fn_size" to null,
        "readability/function" to null,
        "readability/inheritance" to null,
        "readability/multiline_comment" to null,
        "readability/multiline_string" to null,
        "readability/namespace" to null,
        "readability/nolint" to null,
        "readability/nul" to null,
        "readability/strings" to null,
        "readability/todo" to null,
        "readability/utf8" to null,
        "runtime/arrays" to null,
        "runtime/casting" to null,
        "runtime/explicit" to null,
        "runtime/int" to null,
        "runtime/init" to null,
        "runtime/invalid_increment" to null,
        "runtime/member_string_references" to null,
        "runtime/memset" to null,
        "runtime/indentation_namespace" to null,
        "runtime/operator" to null,
        "runtime/printf" to null,
        "runtime/printf_format" to null,
        "runtime/references" to null,
        "runtime/string" to null,
        "runtime/threadsafe_fn" to null,
        "runtime/vlog" to null,
        "whitespace/blank_line" to null,
        "whitespace/braces" to null,
        "whitespace/comma" to null,
        "whitespace/comments" to null,
        "whitespace/empty_conditional_body" to null,
        "whitespace/empty_loop_body" to null,
        "whitespace/end_of_line" to null,
        "whitespace/ending_newline" to EndingNewlineFix(),
        "whitespace/forcolon" to null,
        "whitespace/indent" to null,
        "whitespace/line_length" to null,
        "whitespace/newline" to null,
        "whitespace/operators" to null,
        "whitespace/parens" to null,
        "whitespace/semicolon" to null,
        "whitespace/tab" to null,
        "whitespace/todo" to null
    )

    /**
     * Get the quick fix for a given cpplint rule name.
     *
     * @param name The cpplint rule name (e.g., "whitespace/ending_newline")
     * @return The quick fix, or null if no fix is available for this rule
     */
    fun get(name: String): LocalQuickFix? {
        return fixes[name]
    }

    /**
     * Helper method to get document from problem descriptor.
     * Used by quick fix implementations.
     */
    fun getDocument(project: Project, problemDescriptor: ProblemDescriptor): Document? {
        val psiElement = problemDescriptor.endElement ?: return null
        val psiFile = psiElement.containingFile ?: return null
        val psiDocumentManager = PsiDocumentManager.getInstance(project)
        return psiDocumentManager.getDocument(psiFile)
    }
}
