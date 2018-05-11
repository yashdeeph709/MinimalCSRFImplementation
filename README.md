# MinimalCSRFImplementation
This is a most minimal CSRF implementation in jsp and servlet. I am not good with jsp servlet so its clumsy. But csrf is working.
1. At login you create a csrf token in session.
2. you add a hidden field in every form of your jsp.
3. At any request check that this token in request parameter is same as the one in request session.
