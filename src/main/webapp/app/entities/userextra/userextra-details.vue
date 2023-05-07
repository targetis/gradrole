<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="userextra">
        <h2 class="jh-entity-heading" data-cy="userextraDetailsHeading">
          <span v-text="$t('gradroleApp.userextra.detail.title')">Userextra</span> {{ userextra.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('gradroleApp.userextra.middlename')">Middlename</span>
          </dt>
          <dd>
            <span>{{ userextra.middlename }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.userextra.jobrole')">Jobrole</span>
          </dt>
          <dd>
            <span>{{ userextra.jobrole }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.userextra.dob')">Dob</span>
          </dt>
          <dd>
            <span>{{ userextra.dob }}</span>
          </dd>
          <dt>
            <span v-text="$t('gradroleApp.userextra.user')">User</span>
          </dt>
          <dd>
            {{ userextra.user ? userextra.user.login : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="userextra.id"
          :to="{ name: 'UserextraEdit', params: { userextraId: userextra.id } }"
          tag="button"
          class="btn btn-primary"
        >
          <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./userextra-details.component.ts"></script>
