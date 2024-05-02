## Source Code for Legacy SkySim Engine
Public release of SkySim's legacy core plugin: `SkySimEngine`
(Personal Note: this shit sucks ass, just grab super's core)

**DISCLAIMER** No claim of suitability, guarantee, or any warranty whatsoever is provided. The software is provided "as-is".

**This version of the Core is based on Spectaculation Skyblock Remake project by superischroma** https://github.com/superischroma/Spectaculation

# Background
The version of SkySim Engine contained in this repository was once used in production (**April-May 2022**). However, as of the present day, most of the code within this repository has become obsolete and deprecated. It no longer reflects the standards or practices of modern SkySim Network. The codebase suffers from **poor optimization, bad OOP practices, badly written code**, and overall inefficient as a whole.

# Continuation Disclaimer
While continuation of this core is permitted to some extent, it is strongly discouraged due to its outdated and poorly written nature. Parts of this codebase that remain in use in modern SkySim, such as some items and boss fights, **are not allowed** to be redistributed or monetized without explicit permission.

# Side Notes
It's important to note that SkySim has since moved away from this **Spectaculation-based plugin** (With the **Mining Update**, June of 2022). The modern day SkySim Core, codenamed "`SkySim-Coral`," has been entirely rewritten from scratch to address the shortcomings and absolute dumpsterfire nature of its predecessor (And to scale better).

**IMPORTANT:** As this `Spectaculation` based SkySimEngine was developed in parallel with the `SkySim-Coral` based SkySimEngine with the intention of waiting and testing until the new Coral engine was mature enough for production use, many coding styles are **completely inconsistent and may appear unconventional to work with**.

# Recommended Actions
For those interested in building upon or modifying this legacy core, proceed with caution, it is not worth it. Consider the following recommendations:

- **Reconstruct Build Tools**: The embedded `pom.xml` in the JAR does not belong to this plugin, you should use **Gradle** instead, I do not recommend Maven for this kind of stuff.
- **Compiler Issues**: As the ORIGINAL source code has been lost to time (deleted), this codebase was decompiled and evaluated from the Legacy, non-obfucasted build (JAR) of legacy SkySim Engine
- **Review and Refactor**: Before continuing development, thoroughly review the existing codebase and refactor where necessary to adhere to normal coding standards.
- **Update Dependencies**: Update any outdated dependencies or libraries to ensure compatibility and security.
- **Evaluate Performance**: Assess the performance of the existing code and identify areas for improvement, particularly in terms of optimization and efficiency.
- **Careful with Production Usage**: As the core was used in SkySim back in 2022, many bugs, exploits and dupes were identified but remain unresolved within this repository.
- **Handling Unexpected Behaviours**: This build was exclusively intended for deployment on SkySim Production servers. As a result, the behavior of the Core is completely unpredictable (missing specific runtime libraries) when utilized in other contexts. (ANY **DOS/NT-BASED** OPERATING SYSTEM IS **NOT RECOMMENDED**)
- **Understand Limitations**: Understand the limitations and constraints of this legacy core, and proceed with realistic expectations.

# ABSOLUTELY NO SUPPORT WILL BE PROVIDED!
Please note that we WILL NOT offer any assistance regarding the continuation or any other aspect of this core. As the original code has been lost and what I did back then is questionable and I may forget almost everything I did.

© 2024 SkySim Network, all rights reserved
