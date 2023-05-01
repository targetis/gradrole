<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="userExt">
        <h2 class="jh-entity-heading" data-cy="userExtDetailsHeading">
          <span v-text="$t('gradroleApp.userExt.detail.title')">UserExt</span> {{ userExt.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('gradroleApp.userExt.middleName')">Middle Name</span>
          </dt>
          <dd>
            <span>{{ userExt.middleName }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.userExt.jobRole')">Job Role</span>
          </dt>
          <dd>
            <span>{{ userExt.jobRole }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.userExt.dateOfBirth')">Date Of Birth</span>
          </dt>
          <dd>
            <span>{{ userExt.dateOfBirth }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.userExt.user')">User</span>
          </dt>
          <dd>
            {{ userExt.user ? userExt.user.login : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="userExt.id"
          :to="{ name: 'UserExtEdit', params: { userExtId: userExt.id } }"
          tag="button"
          class="btn btn-primary"
        >
          <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./user-ext-details.component.ts"></script>
