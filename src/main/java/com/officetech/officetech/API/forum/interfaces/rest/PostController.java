package com.officetech.officetech.API.forum.interfaces.rest;
import com.officetech.officetech.API.forum.domain.services.PostCommandService;
import com.officetech.officetech.API.forum.interfaces.rest.resources.CreateNewPostResource;
import com.officetech.officetech.API.forum.interfaces.rest.transform.CreateNewPostCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/forum", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Forum")
public class PostController {
    private final PostCommandService postCommandService;

    public PostController(PostCommandService postCommandService) {
        this.postCommandService = postCommandService;
    }

    @PostMapping("/new-post")
    public ResponseEntity<Boolean> createPost(@RequestBody CreateNewPostResource resource) {
        var command = CreateNewPostCommandFromResourceAssembler.toCommandFromResource(resource);
        return ResponseEntity.ok(postCommandService.handle(command));
    }
}
