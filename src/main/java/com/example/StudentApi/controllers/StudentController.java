package com.example.StudentApi.controllers;

import com.example.StudentApi.models.StudentModel;
import com.example.StudentApi.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")




public class StudentController {

    @Autowired
    private StudentService service;

    @Operation(summary = "Criação de um novo estudante", description = "Operação resposável pela criação de um novo estudante no DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação quando o usuário é criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentModel.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Sucesso",
                                    value = "{\"id\": \"123e4567-e89b-12d3-a456-426614174000\", \"name\": \"João Silva\", \"email\": \"joao.silva@example.com\", \"birthDate\": \"2000-01-01\", \"registrationNumber\": \"123456\"}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Operação quando é preenchido de forma errado os campos",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Erro",
                                    value = "{\"error\": \"Campos obrigátórios não estão preenchidos corretamente, favor verificar\"}"
                            )
                    )
            )
        }
    )

    @PostMapping
    public StudentModel createStudent(@RequestBody StudentModel student) {
        return service.createStudent(student);
    }

    @Operation(summary = "Pesquisar todos estudantes", description = "Operação resposável por puxar lista de todos os estudantes do DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação quando o sistema faz a consulta com sucesso aos estudantes",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentModel.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Lista de Estudantes",
                                    value = "[{\"id\": \"123e4567-e89b-12d3-a456-426614174000\", \"name\": \"João Silva\", \"email\": \"joao.silva@example.com\", \"birthDate\": \"2000-01-01\", \"registrationNumber\": \"123456\"}]"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Operação quando ocorre erro ao solitar a consulta",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Erro",
                                    value = "{\"error\": \"Erro interno do servidor, favor comunicar a TI\"}"
                            )
                    )
            )
        }
    )

    @GetMapping
    public List<StudentModel> getAllStudents() {
        return service.getAllStudents();
    }

    @Operation(summary = "Pesquisar estudante por ID", description = "Operação resposável por puxar estudante pelo seu ID armazenado no DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação quando o sistema acha o estudante com o devido ID",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentModel.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Estudante",
                                    value = "{\"id\": \"123e4567-e89b-12d3-a456-426614174000\", \"name\": \"João Silva\", \"email\": \"joao.silva@example.com\", \"birthDate\": \"2000-01-01\", \"registrationNumber\": \"123456\"}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Operação quando o Dado ID é inválido",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Erro",
                                    value = "{\"error\": \"ID inválido\"}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Operação quando não é encontrado nenhum estudante com o ID digitado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Não Encontrado",
                                    value = "{\"error\": \"Estudante não encontrado\"}"
                            )
                    )
            )
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable UUID id) {
        Optional<StudentModel> student = service.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar informações de um estudante", description = "Operação resposável por atualizar informações do estudante pelo seu ID armazenado no DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação quando o sistema acha o estudante com o devido ID e atualiza as informações",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentModel.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Sucesso",
                                    value = "{\"id\": \"123e4567-e89b-12d3-a456-426614174000\", \"name\": \"Bruno Silva\", \"email\": \"joao.silva@example.com\", \"birthDate\": \"2000-01-01\", \"registrationNumber\": \"654321\"}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Operação quando o Dado ID é inválido",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Erro",
                                    value = "{\"error\": \"ID inválido\"}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Operação quando não é encontrado nenhum estudante com o ID digitado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Não Encontrado",
                                    value = "{\"error\": \"Estudante não encontrado\"}"
                            )
                    )
            )
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<StudentModel> updateStudent(@PathVariable UUID id, @RequestBody StudentModel student) {
        return ResponseEntity.ok(service.updateStudent(id, student));
    }

    @Operation(summary = "Deletar um estudante", description = "Operação resposável por deletar um estudante pelo seu ID armazenado no DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação quando o estudante é deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Operação quando o Dado ID é inválido",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Erro",
                                    value = "{\"error\": \"ID inválido\"}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Operação quando não é encontrado nenhum estudante com o ID digitado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta de Não Encontrado",
                                    value = "{\"error\": \"Estudante não encontrado\"}"
                            )
                    )
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}