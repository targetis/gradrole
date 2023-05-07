<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="student">
        <h2 class="jh-entity-heading" data-cy="studentDetailsHeading">
          <span v-text="$t('gradroleApp.student.detail.title')">Student</span> {{ student.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('gradroleApp.student.firstname')">Firstname</span>
          </dt>
          <dd>
            <span>{{ student.firstname }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.student.lastname')">Lastname</span>
          </dt>
          <dd>
            <span>{{ student.lastname }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.student.email')">Email</span>
          </dt>
          <dd>
            <span>{{ student.email }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.student.dob')">Dob</span>
          </dt>
          <dd>
            <span>{{ student.dob }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.student.course')">Course</span>
          </dt>
          <dd>
            <span>{{ student.course }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="student.id"
          :to="{ name: 'StudentEdit', params: { studentId: student.id } }"
          tag="button"
          class="btn btn-primary"
        >
          <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./student-details.component.ts"></script>
