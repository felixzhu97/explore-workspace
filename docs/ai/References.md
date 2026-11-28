# References

Explore AI — 对话与智能体。总册 [../References.md](../References.md)；身份主键 [../iam/References.md](../iam/References.md)。

## 目录

1. [概念释义](#概念释义)
2. [规范性引用文件](#规范性引用文件)
   1. [国家标准与行业标准](#国家标准与行业标准)
   2. [国际标准](#国际标准)
   3. [协议与技术规范](#协议与技术规范)
3. [参考文献](#参考文献)
   1. [指南与最佳实践](#指南与最佳实践)
   2. [产品与平台文档](#产品与平台文档)
   3. [专著](#专著)
   4. [论文](#论文)

## 概念释义

1. **RAG** — 先检索再生成，锚定外部知识。
   https://aws.amazon.com/what-is/retrieval-augmented-generation/

2. **LLM** — 规模化预训练的通用文本模型。
   https://www.cloudflare.com/learning/ai/what-is-large-language-model/

3. **Embedding** — 文本向量表示，供检索与相似度。
   https://huggingface.co/learn/nlp-course/chapter1/4

4. **Tool calling** — 按 schema 调用外部工具。
   https://platform.openai.com/docs/guides/function-calling

5. **MCP** — 工具与上下文互操作协议。
   https://modelcontextprotocol.io/

6. **A2A** — Agent 发现与任务协作。
   https://a2a-protocol.org/

7. **SSE** — 服务端单向事件流。
   https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events

8. **Prompt injection** — 输入劫持模型与工具权限。
   https://owasp.org/www-project-top-10-for-large-language-model-applications/

## 规范性引用文件

> 注日期引用仅该版本适用；不注日期引用适用最新版（含修改单）。
>
> OAuth / OIDC / JWT / SCIM 实现细节见 IAM 分册。

### 国家标准与行业标准

1. GB/T 35273—2020 信息安全技术 个人信息安全规范
   https://openstd.samr.gov.cn/bzgk/std/newGbInfo?hcno=4568F276E0F8346EB0FBA097AA0CE05E

2. GB/T 22239—2019 信息安全技术 网络安全等级保护基本要求
   https://std.samr.gov.cn/

### 国际标准

1. ISO/IEC 22989:2022 AI concepts and terminology
   https://www.iso.org/standard/74296.html

2. ISO/IEC 23894:2023 AI risk management
   https://www.iso.org/standard/77304.html

3. ISO/IEC 42001:2023 AI management system
   https://www.iso.org/standard/81230.html

4. ISO/IEC 27001:2022 ISMS Requirements
   https://www.iso.org/standard/27001

### 协议与技术规范

1. IETF RFC 6749:2012, OAuth 2.0
   https://www.rfc-editor.org/rfc/rfc6749

2. OpenID Connect Core 1.0
   https://openid.net/specs/openid-connect-core-1_0.html

3. IETF RFC 7636:2015, PKCE
   https://www.rfc-editor.org/rfc/rfc7636

4. IETF RFC 7519:2015, JWT
   https://www.rfc-editor.org/rfc/rfc7519

5. IETF RFC 7643:2015, SCIM Core Schema
   https://www.rfc-editor.org/rfc/rfc7643

6. IETF RFC 7644:2015, SCIM Protocol
   https://www.rfc-editor.org/rfc/rfc7644

7. WHATWG Server-Sent Events
   https://html.spec.whatwg.org/multipage/server-sent-events.html

8. IETF RFC 6455:2011, WebSocket
   https://www.rfc-editor.org/rfc/rfc6455

9. OpenAPI Specification 3.1
   https://spec.openapis.org/oas/latest.html

10. JSON Schema
   https://json-schema.org/specification

11. Model Context Protocol
   https://modelcontextprotocol.io/specification

12. Agent-to-Agent Protocol
   https://a2a-protocol.org/

## 参考文献

### 指南与最佳实践

1. OWASP LLM Top 10
   https://owasp.org/www-project-top-10-for-large-language-model-applications/

2. OWASP ASVS
   https://owasp.org/www-project-application-security-verification-standard/

3. OWASP LLM Prompt Injection Cheat Sheet
   https://cheatsheetseries.owasp.org/cheatsheets/LLM_Prompt_Injection_Prevention_Cheat_Sheet.html

4. Google AIP
   https://google.aip.dev/

5. Google AIP-136
   https://google.aip.dev/136

6. LangChain RAG evaluation
   https://python.langchain.com/docs/guides/evaluation/

### 产品与平台文档

1. Spring AI
   https://docs.spring.io/spring-ai/reference/

2. Spring Security OAuth2 Resource Server
   https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/

3. DeepSeek API
   https://api-docs.deepseek.com/

4. OpenAI Platform
   https://platform.openai.com/docs

5. Ollama
   https://ollama.com/

6. Serper.dev
   https://serper.dev/

7. Resend
   https://resend.com/docs

8. Datadog
   https://docs.datadoghq.com/

9. LaunchDarkly
   https://docs.launchdarkly.com/

10. Tesseract OCR
   https://tesseract-ocr.github.io/

### 专著

1. Jurafsky & Martin. *Speech and Language Processing*. 3rd ed. draft
   https://web.stanford.edu/~jurafsky/slp3/

2. Manning et al. *Introduction to Information Retrieval*. CUP, 2008
   https://nlp.stanford.edu/IR-book/

3. Goodfellow et al. *Deep Learning*. MIT Press, 2016
   https://www.deeplearningbook.org/

### 论文

1. Vaswani et al. Attention Is All You Need. *NeurIPS*, 2017
   https://arxiv.org/abs/1706.03762

2. Lewis et al. Retrieval-Augmented Generation…. *NeurIPS*, 2020
   https://arxiv.org/abs/2005.11401

3. Yao et al. ReAct. *ICLR*, 2023
   https://arxiv.org/abs/2210.03629

4. Schick et al. Toolformer. *NeurIPS*, 2023
   https://arxiv.org/abs/2302.04761

5. Wei et al. Chain-of-Thought Prompting. *NeurIPS*, 2022
   https://arxiv.org/abs/2201.11903

6. Brown et al. Language Models are Few-Shot Learners. *NeurIPS*, 2020
   https://arxiv.org/abs/2005.14165

7. Ouyang et al. InstructGPT / RLHF. *NeurIPS*, 2022
   https://arxiv.org/abs/2203.02155
