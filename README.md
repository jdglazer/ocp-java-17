# ocp-java-17

My personal repository for practicing OCP Java 17 concepts

Since I have already done an in depth review of the topics through the late summer and early fall of 2026, I have generated a tool to pick my next chapter of review (pseudo) randomly. There is no need to respect pre-requisite topics at this point. Here's how I build it on my Ubuntu Desktop:

```bash
cd utils/chapter_selector
javac GenerateDailyChapterToReview.java
jar cfm generate-daily-chapter.jar manifest.txt GenerateDailyChapterToReview.class
```

I stage the jar on my desktop so when I click it, it will run and give me my next chapter to review. This keeps things interesting.

